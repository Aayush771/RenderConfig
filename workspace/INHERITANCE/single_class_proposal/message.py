# Message class contains all message related variables/functions.
class Message:
    MSG_TYPE_TEXT = 1
    MSG_TYPE_VOICE = 2
    MSG_TYPE_IMAGE = 3

    # Python doesn't support private variables like Java/C++
    # Convention is that if you prefix a variable with _var or __var, it is considered private.
    def __init__(self):
        # Common fields.
        self.__message_id = None
        self.__sender = None
        self.__receiver = None
        self.__message_type = None

        # More common fields, can be extended to many more.
        self.__delivery_status = None
        self.__return_receipt_required = None


        # Fields related to text message.
        self.__text_message_content = None

        # Fields related to voice message.
        self.__voice_message_content = None
        self.__voice_duration_in_sec = None
        self.__voice_quality_in_kbps = None

        # Fields related to image message.
        self.__image_message_content = None
        self.__image_resolution = None
        self.__image_metadata = None

    def set_sender(self, sender):
        self.__sender = sender

    def set_receiver(self, receiver):
        self.__receiver = receiver

    def set_text_message_content(self, text_message_content: str):
        self.__message_type = Message.MSG_TYPE_TEXT
        self.__text_message_content = text_message_content

    def set_voice_message_content(self, voice_message_content: str,
                                  voice_duration: int, voice_quality: int):
        self.__message_type = Message.MSG_TYPE_VOICE
        self.__voice_message_content = voice_message_content
        self.__voice_duration_in_sec = voice_duration
        self.__voice_quality_in_kbps = voice_quality

    def set_image_message_content(self, image_message_content: bytes,
                                  image_resolution, image_metadata):
        self.__message_type = Message.MSG_TYPE_IMAGE
        self.__image_message_content = image_message_content
        self.__image_resolution = image_resolution
        self.__image_metadata = image_metadata

    def get_sender(self):
        return self.__sender

    def get_receiver(self):
        return self.__receiver

    def get_text_message_content(self):
        return self.__text_message_content

    def get_voice_message_content(self):
        return self.__voice_message_content

    def get_image_message_content(self):
        return self.__image_message_content

    def get_text_message_size(self):
        return len(self.__text_message_content)

    def get_voice_message_size(self):
        return len(self.__voice_message_content)

    def get_image_message_size(self):
        return len(self.__image_message_content)


