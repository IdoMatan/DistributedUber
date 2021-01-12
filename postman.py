import requests
import time

json_message = {"key": "value"}


json_message = [
{"first_name": "shug1", "last_name": "lag", "phone_number": "444", "origin": "cityB", "destination": "cityA", "departure_date": "2021-1-16", "vacancies": 2, "pd": 0}
]
url = 'localhost:8083/new_ride'

for j in json_message:
    r = requests.post(url, json=j)
    print(r.status_code)


"localhost:8083/new_ride"
"localhost:8093/ride/book/path_planning"


{"first_name": "shug", "last_name": "ch",  "origin": ["cityA", "cityB"], "destination": ["cityC", "cityA"], "departure_date": ["2021-1-16", "2021-1-16"]},


def generate_json(ride=):
    json_message = {}