insert into screenings (ts_from, title, room_id, seats) values
                                                            ('2024-01-08 20:20:00 +0200', 'The Room', 1, array(select * from generate_series(1,50))),
                                                            ('2024-01-07 19:10:00 +0200', 'Yellow Submarine', 1, array(select * from generate_series(1,50))),
                                                            ('2024-01-08 20:20:00 +0200', 'Atonement', 2, array(select * from generate_series(1,50))),
                                                            ('2024-01-08 17:20:00 +0200', 'One Flew Over the Cuckoo''s Nest', 2, array(select * from generate_series(1,50))),
                                                            ('2024-01-07 16:50:00 +0200', 'Star Wars: Episode IV – A New Hope', 3, array(select * from generate_series(1,50))),
                                                            ('2024-01-08 20:20:00 +0200', 'Interstellar', 3, array(select * from generate_series(1,50))),
                                                            ('2023-01-08 20:20:00 +0200', 'The Nutcracker', 3, array(select * from generate_series(1,50)));
