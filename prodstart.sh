for i in {0..1}
do
  for j in {1..1000}
  do
    java -cp libs/selenium-server-standalone-3.4.0.jar:target/train-booking-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
      com.rambaud.train.train_booking.App \
      "$1" "$2" "$3" "$4" "$5" "$i"
    rc=$?
    echo "exit code is $rc"
    if [ $rc == 0 ]
    then
      echo "Booking success stop routine"
      break
    fi
  done
done
