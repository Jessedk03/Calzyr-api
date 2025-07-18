set -a
source .env
set +a

read -p "Out of order? y/n " yn

if [ "$yn" == "y" ] || [ "$yn" == "Y" ]; then
  flyway -outOfOrder=true migrate
elif [ "$yn" == "n" ] || [ "$yn" == "N" ]; then
  flyway -outOfOrder=false migrate
fi