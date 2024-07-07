from PIL import Image

from message import Message


# File contains a list of functions that will be called from
# the Android app through a REST API server.
# For simplicity, imagine these functions will be called directly
# from your Android app.
# Example: If you click send message in your Android app,
# send_message() function will be called.
def send_message(message: Message):
    # 1. Discard invalid messages.
    if not message.is_valid():
        raise Exception('Invalid message')

    # 2. Ensure content is not offensive.
    check_for_offensive_content(message)

    # 3. Store the message safely.
    store_message(message)

    # 4. Deliver the message to the receiver.
    deliver_message_to_receiver(message)

def store_message(message: Message):
    # Write logic to store the message
    print ('Message {} stored successfully'.format(message))

def check_for_offensive_content(message: Message):
    if (message.is_offensive()):
        raise Exception('Abusive language used; Discard')

def deliver_message_to_receiver(message: Message):
    # Logic to actually send the message to the user. It may happen through some queueing mechanism.
    # Out of syllabus for this exercise :')

    # If the message is too large, don't deliver the message directly.
    # For now, drop the message, in future it can be sent as a link to a storage bucket.
    if (message.is_too_large()):
        raise Exception('Message too large to send {}', message.get_message_size())

    print ('Message "{}" delivered successfully to {}'.format(message.get_message_content(),
                                                              message.get_receiver()))
