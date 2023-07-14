CREATE TABLE screenings(
    screening_id SERIAL NOT NULL,
    ts_from timestamp with time zone,
    title varchar,
    room_id INT NOT NULL,
    seats INT[],
    PRIMARY KEY (screening_id)
);

CREATE TABLE bookings(
    screening_id INT NOT NULL,
    seat INT NOT NULL,
    ticket_price DECIMAL(10,2),
    booking_name varchar,
    PRIMARY KEY (screening_id, seat)
);

