#!/bin/bash

ROOT_UID=0
SUCCESS=0
E_USEREXISTS=70

# Run as root, of course. (this might not be necessary, because we have to run the script somehow with root anyway)
if [ "$UID" -ne "$ROOT_UID" ]
then
  echo "Must be root to run this script."
  exit $E_NOTROOT
fi

#test, if both argument are there
if [ $# -eq 2 ]; then
username="mailuser"
pass=$1
choice=$2

	useradd -p `mkpasswd "$pass"` -d /home/"$username" -m -g users -s /bin/bash "$username"

	echo "the account is setup"

	if [ $choice = "y" ]; then
        mkdir /home/$username/public_html
        mkdir /home/$username/public_html/emails
        mkdir /home/$username/public_html/receivers
        mkdir /home/$username/public_html/uploads
        echo "folders are setup"

	else
	    echo "okey"
	fi

else
        echo  " this programm needs 2 arguments you have given $# "
        echo  " you have to call the script $0 username and the pass "
fi

exit 0