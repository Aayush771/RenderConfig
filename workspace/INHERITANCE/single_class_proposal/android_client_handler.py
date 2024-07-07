from PIL import Image

from message import Message

# File contains a list of functions that will be called from
# the Android app through a REST API server.
# For simplicity, imagine these functions will be called directly
# from your Android app.
# Example: If you click send message in your Android app,
# send_text_message() function will be called.
def send_text_message(message: Message):
    # 1. Discard empty strings.
    if (message.get_text_message_content() == ''):
        raise Exception('Cannot send empty string')

    # 2. Ensure content is not offensive.
    check_for_offensive_content(message.get_text_message_content())

    # 3. Store the message safely.
    store_message(message)

    # 4. Deliver the message to the receiver.
    deliver_text_message_to_receiver(message)

def send_image_message(message: Message):
    # 1. Discard images that are empty.
    if (message.get_image_message_content() is None):
        raise Exception('Cannot send empty image')

    # 2. Ensure content is not offensive.
    check_for_offensive_image_content(message.get_image_message_content())

    # 3. Store the message safely.
    store_message(message)

    # 4. Deliver the message to the receiver.
    deliver_image_message_to_receiver(message)


def store_message(message: Message):
    # Write logic to store the message
    print ('Message {} stored successfully'.format(message))

def check_for_offensive_content(content: str):
    # Some dummy logic to check if the message contains unparliamentary words.
    if (content.find('abuse') != -1):
        raise Exception('Abusive language used; Discard')

def check_for_offensive_image_content(image_content: bytes):
    # Dummy logic to check for offensive images.
    print ('Check for offensive image content')

def deliver_text_message_to_receiver(message: Message):
    # Logic to actually send the message to the user. It may happen through some queueing mechanism.
    # Out of syllabus for this exercise :')

    # If the message is too large, don't deliver the message directly.
    # For now, drop the message, in future it can be sent as a link to a storage bucket.
    if (message.get_text_message_size() > 100):
        raise Exception('Message too large to send {} > 100 bytes', message.get_message_size())

    print ('Message "{}" delivered successfully to {}'.format(message.get_text_message_content(),
                                                              message.get_receiver()))
    pass

def deliver_image_message_to_receiver(message: Message):
    print ('Image message delivered successfully to {}'.format(message.get_receiver()))
    print ('Feel free to write code that will render the image,'
           ' for now we will print the bytes')
    print ('Image bytes: {}', message.get_image_message_content())

    pass
