Step 1.
Create a producer for a queue, this is a mandatory step for you to be start producing since only 1 producer is allowed per queue
curl --location --request POST 'http://localhost:8080/queue-lib/addProducer' \
--header 'Content-Type: application/json' \
--data-raw '{
   "producerName":"prod1",
   "queueName":"Queue1"
}'
Step 2:
Add a subscriber to the queue, you can keep adding multiple subscribers

curl --location --request POST 'http://localhost:8080/queue-lib/addSubscriber' \
--header 'Content-Type: application/json' \
--data-raw '{

   "queueName":"Queue1",
   "subscriberName":"Sub1"
}'


{

   "queueName":"Queue1",
   "subscriberName":"Sub2"
}

Step 3:
You can start producing to the queue once you are the creater, ideally there is an auth which should be returned from the previous payload and should be passed in this but for simplicity the producer gives his name in the payload


curl --location --request POST 'http://localhost:8080/queue-lib/addMessageToQueue' \
--header 'Content-Type: application/json' \
--data-raw '{
   "producerName":"producer1",
   "queueName":"Queue1",
   "queueMessage":{
       "queueHeader":"{\"countryCode\":\"IN\",\"facilityNum\":8877}",
       "queueBody":"{\"name\":\"Hritik\",\"surname\":\"Roshan\"}"

   }
}'

When you produce this message, you will see subscribers subscribing and Logging the messages


Step 4:remove a subscriber 
curl --location --request POST 'http://localhost:8080/queue-lib/removeSubscriber' \
--header 'Content-Type: application/json' \
--data-raw '{

   "queueName":"Queue1",
   "subscriberName":"Sub2"
}'
