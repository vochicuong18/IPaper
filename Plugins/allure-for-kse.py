# import os
# import shutil
# import random
# import json
# import re
# import xmltodict
# import argparse
# import html
# from datetime import datetime

# parser = argparse.ArgumentParser(description="Katalon")
# parser.add_argument('--platform', type=str, required=True, help="platform")
# args = parser.parse_args()

# REPORT_FOLDER = '../Reports'
# SCREENSHOT_FOLDER = '../ErrorScreen'
# ALLURE_RESULT_FOLDER = f'allure-results-{args.platform}'
# LOG_FILE = f'reports_{args.platform}.log'

# def get_report_folders():
#     folders = []
#     print(os.path.join(REPORT_FOLDER, LOG_FILE))
#     with open(os.path.join(REPORT_FOLDER, LOG_FILE)) as f:
#         lines = f.readlines()
#     for line in lines:
#         folders.append(line.strip())
#     return folders

# def generate_json_report(report_folder):
#     report_file_log = os.path.join(report_folder, 'execution0.log')
#     report_file_xml = os.path.join(report_folder, 'execution0.xml')
#     report_file_json = os.path.join(report_folder, 'execution0.json')

#     if os.path.exists(report_file_xml):
#         os.remove(report_file_xml)
#     shutil.copy2(report_file_log, report_file_xml)

#     with open(report_file_xml, 'r') as f:
#         xml_data = f.read().replace('</log>', '') + '</log>'
#         json_data = json.dumps(xmltodict.parse(xml_data))

#     with open(report_file_json, 'w') as f:
#         f.write(json_data)

#     return json.loads(json_data)

# def convert_time(timestamp_ms):
#     timestamp_s = int(timestamp_ms) / 1000
#     return datetime.fromtimestamp(timestamp_s).strftime('%d-%m-%Y %H:%M:%S')

# def get_start_date(report_folder):
#     records = generate_json_report(report_folder)['log']['record']
#     for record in records:
#         if record['method'] == 'startTest':
#             return convert_time(record['millis'])

# def is_action_step(message):
#     return bool(re.match(r'^\w+\.\w+\(.*\)$', message))

# def get_testcases(report_folder):
#     try:
#         records = generate_json_report(report_folder)['log']['record']
#     except FileNotFoundError:
#         print(f'[WARNING] Không tìm thấy file trong folder: {report_folder}')
#         return []
#     except Exception as e:
#         print(f'[ERROR] Có lỗi xảy ra khi đọc folder {report_folder}: {str(e)}')
#         return []

#     testcase = []
#     testcase_temp = {}
#     steps_temp = []
#     current_step = {}
#     inside_step = False
#     logs_temp = []

#     for record in records:
#         method = record.get('method')
#         level = record["level"]  # Lấy level (FAILED, PASSED, ERROR)

#         if method == 'startTest':
#             testcase_temp['name'] = record['message'].replace('Start Test Case : ', '')
#             testcase_temp['start_time'] = int(record['millis'])
#             testcase_temp['date'] = get_start_date(report_folder)

#         elif method == 'startKeyword':
#             message = html.unescape(record['message'].replace('Start action : ', ''))
#             if is_action_step(message):
#                 current_step = {
#                     "name": message,
#                     "start_time": int(record['millis']),
#                     "status": "passed",
#                     "steps": []
#                 }
#                 inside_step = True
#                 logs_temp = []
#             else:
#                 inside_step = False  # bỏ qua nếu không phải action UI

#         elif method == 'logMessage' and inside_step:
#             if 'message' in record:
#                 log_status = "failed" if level in ["FAILED", "ERROR"] else "passed"
#                 logs_temp.append({
#                     "name": html.unescape(record['message']),
#                     "status": log_status,
#                     "start": int(record['millis']),
#                     "stop": int(record['millis']) + 1
#                 })

#                 # Nếu có lỗi trong bất kỳ log nào thì đánh dấu bước cha là FAILED
#             if log_status == "failed":
#                 current_step['status'] = "failed"  # Cập nhật trạng thái của current_step thành failed

#         elif method == 'endKeyword' and inside_step:
#             if current_step:
#                 current_step['end_time'] = int(record['millis'])
#                 if logs_temp:
#                     current_step['steps'] = logs_temp
#                 steps_temp.append(current_step)
#                 current_step = {}
#                 inside_step = False

#         elif method == 'endTest':
#             testcase_temp['end_time'] = int(record['millis'])

#         elif 'Test Cases/' in record['message'] and "Start Test Case :" not in record['message'] and "End Test Case :" not in record['message']:
#             status = record['level']
#             if status in ['PASSED', 'FAILED', 'ERROR']:
#                 testcase_temp['status'] = status.replace('ERROR', 'FAILED')
#                 # if status in ['FAILED', 'ERROR'] and steps_temp:
#                 #     steps_temp[-1]['status'] = 'failed'

#         if 'end_time' in testcase_temp.keys():
#             if steps_temp:
#                 testcase_temp['steps'] = steps_temp
#             testcase.append(testcase_temp)
#             testcase_temp = {}
#             steps_temp = []

#     return testcase

# def convert_to_allure(testcase):
#     uuid = str(random.randint(1000000000, 9999999999) * random.randint(1000, 9999))

#     template = {
#         "name": testcase['name'].split('/')[-1],
#         "status": testcase['status'].lower(),
#         "attachments": [],
#         "start": testcase['start_time'],
#         "stop": testcase['end_time'],
#         "uuid": uuid,
#         "historyId": uuid,
#         "testCaseId": uuid,
#         "fullName": testcase['name'],
#         "labels": [
#             {
#                 "name": "parentSuite",
#                 "value": f"Checklist: {testcase['date']}"
#             }
#         ]
#     }

#     # Nếu testcase có steps
#     if 'steps' in testcase:
#         template['steps'] = []
#         for step in testcase['steps']:
#             step_template = {
#                 "name": step['name'],
#                 "status": step['status'].lower(),
#                 "start": step['start_time'],
#                 "stop": step['end_time']
#             }
#             # Nếu step có steps con (log nhỏ)
#             if 'steps' in step:
#                 step_template['steps'] = []
#                 for sub_step in step['steps']:
#                     sub_step_template = {
#                         "name": sub_step['name'],
#                         "status": sub_step['status'],
#                         "start": sub_step['start'],
#                         "stop": sub_step['stop']
#                     }
#                     step_template['steps'].append(sub_step_template)
#             template['steps'].append(step_template)

#     if testcase['status'] == 'FAILED':
#         # testcase_name_safe = testcase['name'].replace('/', '_')
#         template['attachments'].append({
#             "name": "Lỗi",
#             "source": f"{os.getcwd()}/{SCREENSHOT_FOLDER}/{args.platform}/{testcase['name']}.png",
#             "type": "image/png"
#         })

#     with open(f'{ALLURE_RESULT_FOLDER}/{uuid}-result.json', 'w+', encoding='utf-8') as f:
#         f.write(json.dumps(template, indent=4))


# if __name__ == '__main__':
#     if os.path.exists(ALLURE_RESULT_FOLDER):
#         shutil.rmtree(ALLURE_RESULT_FOLDER)

#     if not os.path.exists(ALLURE_RESULT_FOLDER):
#         os.mkdir(ALLURE_RESULT_FOLDER)

#     for folder in get_report_folders():
#         testcases = get_testcases(folder)
#         for testcase in testcases:
#             # print(testcase)
#             convert_to_allure(testcase)

import os
import shutil
import random
import json
import re
import xmltodict
import argparse
import html
from datetime import datetime

parser = argparse.ArgumentParser(description="Katalon")
parser.add_argument('--platform', type=str, required=True, help="platform")
args = parser.parse_args()

REPORT_FOLDER = '../Reports'
SCREENSHOT_FOLDER = '../ErrorScreen'
ALLURE_RESULT_FOLDER = f'allure-results-{args.platform}'
LOG_FILE = f'reports_{args.platform}.log'

def get_report_folders():
    folders = []
    print(os.path.join(REPORT_FOLDER, LOG_FILE))
    with open(os.path.join(REPORT_FOLDER, LOG_FILE)) as f:
        lines = f.readlines()
    for line in lines:
        folders.append(line.strip())
    return folders

def generate_json_report(report_folder):
    report_file_log = os.path.join(report_folder, 'execution0.log')
    report_file_xml = os.path.join(report_folder, 'execution0.xml')
    report_file_json = os.path.join(report_folder, 'execution0.json')

    if os.path.exists(report_file_xml):
        os.remove(report_file_xml)
    shutil.copy2(report_file_log, report_file_xml)

    with open(report_file_xml, 'r') as f:
        xml_data = f.read().replace('</log>', '') + '</log>'
        json_data = json.dumps(xmltodict.parse(xml_data))

    with open(report_file_json, 'w') as f:
        f.write(json_data)

    return json.loads(json_data)

def convert_time(timestamp_ms):
    timestamp_s = int(timestamp_ms) / 1000
    return datetime.fromtimestamp(timestamp_s).strftime('%d-%m-%Y %H:%M:%S')

def get_start_date(report_folder):
    records = generate_json_report(report_folder)['log']['record']
    for record in records:
        if record['method'] == 'startTest':
            return convert_time(record['millis'])

def is_action_step(message):
    return bool(re.match(r'^\w+\.\w+\(.*\)$', message))

def map_katalon_status_to_allure(katalon_status):
    """Map Katalon status to Allure status"""
    status_mapping = {
        'PASSED': 'passed',
        'FAILED': 'failed',
        'ERROR': 'broken'
    }
    return status_mapping.get(katalon_status, 'unknown')

def get_testcases(report_folder):
    try:
        records = generate_json_report(report_folder)['log']['record']
    except FileNotFoundError:
        print(f'[WARNING] Không tìm thấy file trong folder: {report_folder}')
        return []
    except Exception as e:
        print(f'[ERROR] Có lỗi xảy ra khi đọc folder {report_folder}: {str(e)}')
        return []

    testcase = []
    testcase_temp = {}
    steps_temp = []
    current_step = {}
    inside_step = False
    logs_temp = []

    for record in records:
        method = record.get('method')
        level = record["level"]  # Lấy level (FAILED, PASSED, ERROR)

        if method == 'startTest':
            testcase_temp['name'] = record['message'].replace('Start Test Case : ', '')
            testcase_temp['start_time'] = int(record['millis'])
            testcase_temp['date'] = get_start_date(report_folder)

        elif method == 'startKeyword':
            message = html.unescape(record['message'].replace('Start action : ', ''))
            if is_action_step(message):
                current_step = {
                    "name": message,
                    "start_time": int(record['millis']),
                    "status": "passed",
                    "steps": []
                }
                inside_step = True
                logs_temp = []
            else:
                inside_step = False  # bỏ qua nếu không phải action UI

        elif method == 'logMessage' and inside_step:
            if 'message' in record:
                log_status = map_katalon_status_to_allure(level) if level in ["FAILED", "ERROR"] else "passed"
                logs_temp.append({
                    "name": html.unescape(record['message']),
                    "status": log_status,
                    "start": int(record['millis']),
                    "stop": int(record['millis']) + 1
                })

                # Nếu có lỗi trong bất kỳ log nào thì đánh dấu bước cha tương ứng
                if log_status in ["failed", "broken"]:
                    current_step['status'] = log_status

        elif method == 'endKeyword' and inside_step:
            if current_step:
                current_step['end_time'] = int(record['millis'])
                if logs_temp:
                    current_step['steps'] = logs_temp
                steps_temp.append(current_step)
                current_step = {}
                inside_step = False

        elif method == 'endTest':
            testcase_temp['end_time'] = int(record['millis'])

        elif 'Test Cases/' in record['message'] and "Start Test Case :" not in record['message'] and "End Test Case :" not in record['message']:
            status = record['level']
            if status in ['PASSED', 'FAILED', 'ERROR']:
                testcase_temp['status'] = status
                # if status in ['FAILED', 'ERROR'] and steps_temp:
                #     steps_temp[-1]['status'] = map_katalon_status_to_allure(status)

        if 'end_time' in testcase_temp.keys():
            if steps_temp:
                testcase_temp['steps'] = steps_temp
            testcase.append(testcase_temp)
            testcase_temp = {}
            steps_temp = []

    return testcase

def convert_to_allure(testcase):
    uuid = str(random.randint(1000000000, 9999999999) * random.randint(1000, 9999))

    template = {
        "name": testcase['name'].split('/')[-1],
        "status": map_katalon_status_to_allure(testcase['status']),
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

    # Nếu testcase có steps
    if 'steps' in testcase:
        template['steps'] = []
        for step in testcase['steps']:
            step_template = {
                "name": step['name'],
                "status": step['status'].lower(),
                "start": step['start_time'],
                "stop": step['end_time']
            }
            # Nếu step có steps con (log nhỏ)
            if 'steps' in step:
                step_template['steps'] = []
                for sub_step in step['steps']:
                    sub_step_template = {
                        "name": sub_step['name'],
                        "status": sub_step['status'],
                        "start": sub_step['start'],
                        "stop": sub_step['stop']
                    }
                    step_template['steps'].append(sub_step_template)
            template['steps'].append(step_template)

    if testcase['status'] in ['FAILED', 'ERROR']:
        # testcase_name_safe = testcase['name'].replace('/', '_')
        template['attachments'].append({
            "name": "Lỗi",
            "source": f"{os.getcwd()}/{SCREENSHOT_FOLDER}/{args.platform}/{testcase['name']}.png",
            "type": "image/png"
        })

    with open(f'{ALLURE_RESULT_FOLDER}/{uuid}-result.json', 'w+', encoding='utf-8') as f:
        f.write(json.dumps(template, indent=4))


if __name__ == '__main__':
    if os.path.exists(ALLURE_RESULT_FOLDER):
        shutil.rmtree(ALLURE_RESULT_FOLDER)

    if not os.path.exists(ALLURE_RESULT_FOLDER):
        os.mkdir(ALLURE_RESULT_FOLDER)

    for folder in get_report_folders():
        testcases = get_testcases(folder)
        for testcase in testcases:
            # print(testcase)
            convert_to_allure(testcase)