#!/bin/bash -e
commit_message="$1"
git add . -A
branch=$(git branch 2>/dev/null | grep '^*' | colrm 1 2 | awk '{if ($1 !="") print $1}')
git commit -m "$commit_message"
git push "$branch"
