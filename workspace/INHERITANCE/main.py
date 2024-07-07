from android_client_handler import send_message
from message import Message

if __name__== "__main__":
    # Call the android/iOS handler code to send a message.
    # In real world, these functions will be called from your Flask or Django server methods.
    # Don't worry about them for now - let's live in a simple world :)

    # Amar sends a message.
    message_1 = Message()
    message_1.set_sender('SENDER_Amar')
    message_1.set_receiver('RECEIVER_Abhinaya')
    message_1.set_message_content('Hello, Have you checked out https://blog.crio.do?')

    send_message(message_1)

    # Amulya sends a message.
    message_2 = Message()
    message_2.set_sender('SENDER_Amulya')
    message_2.set_receiver('RECEIVER_Cratos')
    message_2.set_message_content('Hello, Enjoying Learn By Doing')

    send_message(message_2)