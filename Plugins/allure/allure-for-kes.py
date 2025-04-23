import os
import shutil
import random
import json
import re
import xmltodict
import argparse
from datetime import datetime


parser = argparse.ArgumentParser(description="Katalon")
parser.add_argument('--platform', type=str, required=True, help="platform")
args = parser.parse_args()

REPORT_FOLDER = '../../Reports'
SCREENSHOT_FOLDER = '../../ErrorScreen'
ALLURE_RESULT_FOLDER = f'allure-results-{args.platform}'
LOG_FILE = f'reports_{args.platform}.log'

def get_report_folders():
    """
    Đọc file reports_<platform>.log để lấy ra tất cả đường dẫn report của katalon
    """

    folders = []
    print(os.path.join(REPORT_FOLDER, LOG_FILE))
    # Đọc file và lấy đường dẫn report của katalon
    with open(os.path.join(REPORT_FOLDER, LOG_FILE)) as f:
        lines = f.readlines()

    # Remove kí tự \n đi
    for line in lines:
        folders.append(line.replace('\n', ''))

    return folders


def generate_json_report(report_folder):
    """
    Tạo ra file execution0.json từ file execution0.log (xml)
    """

    # Khởi tạo 3 biến cho 3 file .log .xml .json
    report_file_log = os.path.join(report_folder, 'execution0.log')
    report_file_xml = os.path.join(report_folder, 'execution0.xml')
    report_file_json = os.path.join(report_folder, 'execution0.json')

    # Nếu tồn tại file .xml thì xóa và tạo lại
    if os.path.exists(report_file_xml):
        os.remove(report_file_xml)
    shutil.copy2(report_file_log, report_file_xml)

    # Dump dữ liệu từ .xml định dạng json
    with open(report_file_xml, 'r') as f:
        xml_data = f.read().replace('</log>', '') + '</log>'
        json_data = json.dumps(xmltodict.parse(xml_data))

    # Tạo file .json
    with open(report_file_json, 'w') as f:
        f.write(json_data)

    # Return nội dung file .json
    return json.loads(json_data)


def convert_time(timestamp_ms):
    """
    Convert timestamp_ms sang %d-%m-%Y %H:%M:%S
    """

    timestamp_s = int(timestamp_ms) / 1000
    return datetime.fromtimestamp(timestamp_s).strftime('%d-%m-%Y %H:%M:%S')


def get_start_date(report_folder):
    """
    Lấy ra ngày bắt đầu chạy testcase đầu tiên
    """

    records = generate_json_report(report_folder)['log']['record']
    for record in records:
        if record['method'] == 'startTest':
            return convert_time(record['millis'])


def get_testcases(report_folder):
    """
    Trích ra các thông tin cần thiết từ file .json
    """

    records = generate_json_report(report_folder)['log']['record']

    # Khởi tạo mảng testcase. Mảng này sẽ chứa từng testcase_temp
    testcase = []

    # Khởi tạo dict testcase_temp. Dict này sẽ chứa các thông tin testcase trong vòng lặp for
    testcase_temp = {}

    for record in records:

        # Lấy thông tin như testcase_name, start_time, start_date
        if record['method'] == 'startTest':
            testcase_temp['name'] = record['message'].replace('Start Test Case : ', '')
            testcase_temp['start_time'] = record['millis']
            testcase_temp['date'] = get_start_date(report_folder)

        # Lấy thông tin end_time
        elif record['method'] == 'endTest':
            testcase_temp['end_time'] = record['millis']

        # Lấy ra trạng thái PASSED / FAILED của testcase
        elif 'Test Cases/' in record['message'] and "Start Test Case :" not in record['message'] and "End Test Case :" not in record['message']:
            status = record['level']
            if status in ['PASSED', 'FAILED', 'ERROR']:
                testcase_temp['status'] = status.replace('ERROR', 'FAILED')

        # Nếu đã lấy được end_time thì reset testcase_temp về {}
        if 'end_time' in testcase_temp.keys():
            testcase.append(testcase_temp)
            testcase_temp = {}

    return testcase


def convert_to_allure(testcase):
    """
    Hàm này sẽ convert từng testcase sang format của file allure results
    Mỗi 1 case sẽ là 1 file có định dạng {uuid}-result.json
    """

    # Khởi tạo uuid
    uuid = str(random.randint(1000000000, 9999999999) * random.randint(1000, 9999))

    # Template của file {uuid}-result.json
    template = {
        "name": testcase['name'].split('/')[-1],
        "status": testcase['status'],
        "attachments": [],
        "start": testcase['start_time'],
        "stop": testcase['end_time'],
        "uuid": uuid,
        "historyId": uuid,
        "testCaseId": uuid,
        "fullName": testcase['name'],
        "labels": [
            {
                "name": "parentSuite",
                "value": f"Checklist: {testcase['date']}"
            }
        ]
    }

    if testcase['status'] == 'FAILED':
        template['attachments'].append({
            "name": "Lỗi",
            "source": f"{os.getcwd()}/{SCREENSHOT_FOLDER}/{args.platform}/{testcase['name']}.png",
            "type": "image/png"
        })

    # Ghi vào file {uuid}-result.json
    with open(f'{ALLURE_RESULT_FOLDER}/{uuid}-result.json', 'w+') as f:
        f.write(json.dumps(template))


if __name__ == '__main__':
    # Nếu tồn tại folder allure-results thì xóa
    if os.path.exists(ALLURE_RESULT_FOLDER):
        shutil.rmtree(ALLURE_RESULT_FOLDER)

    # Tạo lại folder allure-results mới
    if not os.path.exists(ALLURE_RESULT_FOLDER):
        os.mkdir(ALLURE_RESULT_FOLDER)

    # Loop để bắt đầu {uuid}-result.json
    for folder in get_report_folders():

        # Đưa folder chứa report vào trong get_testcases
        testcases = get_testcases(folder)

        # Loop ra từng case và ghi vào file {uuid}-result.json
        for testcase in testcases:
            print(testcase)
            convert_to_allure(testcase)