from PIL import Image
from android_client_handler import send_text_message, send_image_message
from message import Message

if __name__== "__main__":
    # Call the android/iOS handler code to send a message.
    # In real world, these functions will be called from your Flask or Django server methods.
    # Don't worry about them for now - let's live in a simple world :)

    # Amar sends a text message.
    message_1 = Message()
    message_1.set_sender('SENDER_Amar')
    message_1.set_receiver('RECEIVER_Abhinaya')
    message_1.set_text_message_content('Hello, Have you checked out https://blog.crio.do?')

    send_text_message(message_1)

    # Amulya sends an image message.
    message_2 = Message()
    message_2.set_sender('SENDER_Amulya')
    message_2.set_receiver('RECEIVER_Cratos')

    # Some logic to send the image as bytes. You can ignore it safely.
    img_path = 'single_class_proposal/CrioLogo.png'
    img = Image.open(img_path)
    with open(img_path, "rb") as image:
        f = image.read()
        image_byte = bytes(f)
    message_2.set_image_message_content(image_byte,
                                        image_resolution=img.size, image_metadata=img.mode)
    send_image_message(message_2)

