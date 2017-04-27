#<----Import the methods from tweepy library---->
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream
import json
import sys

#<----Credentials for accessing Twitter API---->
access_token = "*********************************"
access_token_secret = "**************************"
consumer_key = "*********************************"
consumer_secret = "******************************"

#<----Listener for printing the received tweets---->
class StdOutListener(StreamListener):

    def on_data(self, data):
        print data
        return True

    def on_error(self, status):
        print status


if __name__ == '__main__':

    #<----Handling Twitter Authenticatioin and setting up the connection---->
    listener = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, listener)

    #<----Passing the keywords for the comparison you want make on the tweets---->
    sys.argv.pop(0)
    stream.filter(track = sys.argv)