#!/usr/bin/env bash
# build+testing script for linux/WSL -- Works on Debian

# run checkstyle
check_result=$(python3 run_checkstyle.py)
diff_result=$(printf '%s' "$check_result" | grep "Your code has been rated")
sig=$(diff <(printf '%s' "Your code has been rated at 10.00/10 [raw score: 10.00/10]") <(printf '%s' "$diff_result"))

if [ "$sig" = "" ]; then
    printf 'Success: %s\n' "$diff_result"
else
    echo "Failure:"
    printf '%s\n' "$check_result"
fi

# build+run App
mvn clean javafx:run
