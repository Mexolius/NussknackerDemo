CHCP 65001
cd "./src/database_docker"

start docker-compose up -d

cd "../../"

start sbt run

timeout 15

set "$PATH=http://localhost:8080/nussknacker/demo"

curl --location "%$PATH%/screenings" --header "Content-Type: application/json" --data "{ \"from\":  \"2023-06-23 20:00:00 +0000\",\"to\":  \"2025-06-23 21:00:00 +0000\"}"

timeout 2

curl --location "%$PATH%/screenings/details/3"

timeout 2

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"Marek Ślązak-Jaskólski\",\"tickets\": [{\"seat\": 11, \"ticketType\": {\"price\": 25}},{\"seat\": 12, \"ticketType\": {\"price\": 18}},{\"seat\": 13, \"ticketType\": {\"price\": 12.50}}]}"

timeout 2

echo "Should fail - too short name"

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"M Ślązak-Jaskólski\",\"tickets\": [{\"seat\": 25, \"ticketType\": {\"price\": 25}}]}"

timeout 2

echo "Should fail - 2nd part of surname not capitalized"

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"Marek Ślązak-jaskólski\",\"tickets\": [{\"seat\": 25, \"ticketType\": {\"price\": 25}}]}"

timeout 2

echo "Should fail - Seat taken"

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"Marek Ślązak-Jaskólski\",\"tickets\": [{\"seat\": 12, \"ticketType\": {\"price\": 25}}]}"

timeout 2

echo "Should fail - Unsupported ticket price"

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"Marek Ślązak-Jaskólski\",\"tickets\": [{\"seat\": 25, \"ticketType\": {\"price\": 2}}]}"

timeout 2

echo "Should fail - Single seat gap"

curl --location --request PUT "%$PATH%/screenings/book/3" --header "Content-Type:application/json; charset=utf-8" --data-ascii "{\"reservationName\": \"Marek Ślązak-Jaskólski\",\"tickets\": [{\"seat\": 9, \"ticketType\": {\"price\": 25}}]}"
