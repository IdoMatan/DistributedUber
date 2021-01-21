import requests
import time
import random

import names
import numpy as np

import matplotlib.pyplot as plt

class GenerateJson:
    def __init__(self):
        self.first_name = ""
        self.last_name = ""
        self.day = ""
        self.month = ""
        self.phone_number = ""
        self.vacancies = 0
        self.pd = 0
        self.origin = ""
        self.origins = ["cityA", "cityB", "cityC", "cityD"] #, "cityE", "cityF", "cityG", "cityH"]
        self.destination = ""
        self.destinations = ["cityA", "cityB", "cityC", "cityD"] #, "cityE", "cityF", "cityG", "cityH"]

    def new_person(self):
        self.first_name = names.get_first_name()
        self.last_name = names.get_last_name()

    def new_ride(self):
        self.day = str(random.randint(1, 30))
        self.month = str(random.randint(1, 12))

        # self.day = str(random.randint(1, 1))
        # self.month = str(random.randint(1, 1))
        self.origin = random.choice(self.origins)
        self.destination = random.choice(self.destinations)
        while self.destination == self.origin:
            self.destination = random.choice(self.destinations)

    def new_driver_details(self):
        self.phone_number = str(random.randint(1000, 9999))
        self.vacancies = random.randint(1, 10)
        self.pd = random.randint(1, 10)

    def generate_ride_json(self):
        self.new_person()
        self.new_ride()
        self.new_driver_details()
        random_json_message = {"first_name": self.first_name, "last_name": self.last_name, "phone_number": self.phone_number, "origin": self.origin,
                               "destination": self.destination, "departure_date": "2021-" + self.month + "-" + self.day,
                               "vacancies": self.vacancies, "pd": self.pd}
        return random_json_message

    def generate_passenger_json(self):
        self.new_person()
        self.new_ride()
        random_json_message = {"first_name": self.first_name, "last_name": self.last_name, "origin": self.origin,
                               "destination": self.destination, "departure_date": "2021-" + self.month + "-" + self.day}
        return random_json_message

    def generate_passenger_trip_json(self, trips=4):
        self.new_person()
        origins, destinations, departure_dates = self.trip_generation(trips)
        random_json_message = {"first_name": self.first_name, "last_name": self.last_name, "origin": origins,
                               "destination": destinations, "departure_date": departure_dates}
        return random_json_message

    def trip_generation(self, trips):
        origins, destinations, departure_dates = [], [], []
        for i in range(trips):
            self.new_ride()
            origins.append(self.origin)
            destinations.append(self.destination)
            departure_dates.append("2021-" + self.month + "-" + self.day)

        return origins, destinations, departure_dates


def get_snapshot(snapshot_url, host='http://localhost:'):
    snapshot_url_full = host + snapshot_url + '/snapshot'
    request = requests.get(snapshot_url_full)
    if request.status_code == 200:
        print("Content: ", request.content)
    else:
        print(request.status_code)


if __name__ == '__main__':
    n_rides = 100
    generate_json = GenerateJson()
    passengers = 100
    combined = 0
    urls = ["8013", "8023", "8033",  "8053", "8063", "8073", "8083", "8093", "8103", "8113", "8123", "8133"]
    host = 'http://localhost:'

    ride_creation = []
    book_creation = []
    trip_creation = []

    for i in range(combined):
        j = generate_json.generate_ride_json()
        url = host + random.choice(urls) + '/new_ride'
        start_time = time.time()
        r = requests.post(url, json=j)
        ride_creation.append(time.time() - start_time)

        if r.status_code == 500:
            print("Status code: ", r.status_code)
            print("url: ", url, "Json: ", j)

        j = generate_json.generate_passenger_trip_json()
        url = host + random.choice(urls) + '/ride/book/path_planning'
        start_time = time.time()
        r = requests.post(url, json=j)

        j = generate_json.generate_passenger_trip_json()
        url = host + random.choice(urls) + '/ride/book/path_planning'
        start_time = time.time()
        r = requests.post(url, json=j)
        trip_creation.append(time.time() - start_time)

        j = generate_json.generate_passenger_json()
        url = host + random.choice(urls) + '/ride/book/single'
        start_time = time.time()
        r = requests.post(url, json=j)
        trip_creation.append(time.time() - start_time)
        book_creation.append(time.time() - start_time)

    trip_creation = np.array(trip_creation)
    ride_creation = np.array(ride_creation)
    book_creation = np.array(book_creation)

    plt.plot(ride_creation)
    plt.xlabel("Number of rides")
    plt.ylabel("Time to create a ride [Sec]")
    plt.title("Time to create a ride VS Number of saved rides")
    plt.show()

    plt.plot(trip_creation)
    plt.xlabel("Number of rides")
    plt.ylabel("Time to book a trip [Sec]")
    plt.title("Time to book a trip VS Number of saved rides")
    plt.show()

    plt.plot(book_creation)
    plt.xlabel("Number of rides")
    plt.ylabel("Time to book a single [Sec]")
    plt.title("Time to book a single ride VS Number of saved rides")
    plt.show()

    np.save("ride_creation", ride_creation)
    np.save("trip_creation", trip_creation)
    np.save("book_creation", book_creation)



    for i in range(n_rides):
        j = generate_json.generate_ride_json()
        url = host + random.choice(urls) + '/new_ride'
        start_time = time.time()
        r = requests.post(url, json=j)
        # print("Sent to: ", url)
        if r.status_code == 500:
            print("Status code: ", r.status_code)
            print("url: ", url, "Json: ", j)

        # print("Content: ", r.content)
        # print("Generate new ride in: ", time.time() - start_time, " [Sec]")
        ride_creation.append(time.time() - start_time)
    ride_creation = np.array(ride_creation)
    print("ride creation mean: ", ride_creation.mean(), "ride creation std: ", ride_creation.std())
    # get_snapshot(random.choice(urls), host=host)


    for i in range(passengers):

        if bool(random.getrandbits(1)):
            j = generate_json.generate_passenger_trip_json()
            url = host + random.choice(urls) + '/ride/book/path_planning'
            trip = True
        else:
            j = generate_json.generate_passenger_json()
            url = host + random.choice(urls) + '/ride/book/single'
            trip = False

        start_time = time.time()
        r = requests.post(url, json=j)
        # print(r.status_code)
        # print("Content: ", r.content)
        if r.status_code == 500:
            # print("Content: ", r.content)
            print("Status_code: ", r.status_code)
            # print("url: ", url, "Json: ", j)
        if trip: trip_creation.append(time.time() - start_time)
        else: book_creation.append(time.time() - start_time)
        # print("Book response in: ", time.time() - start_time, " [Sec]")

    trip_creation = np.array(trip_creation)
    print("trip creation mean: ", trip_creation.mean(), "trip creation std: ", trip_creation.std())

    book_creation = np.array(book_creation)
    print("book creation mean: ", book_creation.mean(), "book creation std: ", book_creation.std())


    # get_snapshot(random.choice(urls), host=host)
