#!/bin/bash
if [[ $UID != 0 ]]; then
    echo "Please run this script with sudo:"
    echo "sudo $0 $*"
    exit 1
fi
echo "Failover started (slave -> master)."
echo "Replacing config file."
cp -v /var/failover/master.php /var/www/configuration.php
echo "Replace OK."
echo "Restarting apache service."
service apache2 restart
echo "Failover script ended."