set -a
source .env
set +a

command flyway "$@"