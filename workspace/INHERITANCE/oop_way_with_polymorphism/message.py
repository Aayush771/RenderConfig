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

    def set_sender(self, sender):
        self.__sender = sender

    def set_receiver(self, receiver):
        self.__receiver = receiver

    def get_sender(self):
        return self.__sender

    def get_receiver(self):
        return self.__receiver

    # Individual message types have to override this method as there is no default implementation.
    def is_offensive(self):
            raise NotImplementedError;

    def is_valid(self):
        raise NotImplementedError;

    def get_message_type(self):
        return self.__message_type

    def get_message_size(self):
        return len(self.get_message_content())

    # Unless overridden explicitly, we don't consider any message too large.
    # In our case, only TextMessage overrides this method.
    def is_too_large(self):
        return False

    def __str__(self):
        raise NotImplementedError;



class TextMessage(Message):
    def __init__(self):
        super().__init__()
        # Fields related to text message.
        self.__message_content = None
        self.__message_type = Message.MSG_TYPE_TEXT

    def set_message_content(self, text_message_content: str):
        self.__message_content = text_message_content

    def get_message_content(self):
        return self.__message_content

    # For text messages anything more than 100 characters, we are considering is not allowed.
    def is_too_large(self):
        if (self.get_message_size() > 100):
            return True
        else:
            return False

    def is_offensive(self):
        if (self.get_message_content().find('abuse') != -1):
            return True
        else:
            return False

    def is_valid(self):
        if (self.get_message_content() == ''):
            return False
        return True

    def __str__(self):
        # TODO: Override this function to print meaningful debug messages.
        pass


class ImageMessage(Message):
    def __init__(self):
        super().__init__()

        # Fields related to image message.
        self.__message_content = None
        self.__image_resolution = None
        self.__image_metadata = None
        self.__message_type = Message.MSG_TYPE_IMAGE

    def set_message_content(self, image_message_content: bytes,
                                  image_resolution, image_metadata):
        self.__message_content = image_message_content
        self.__image_resolution = image_resolution
        self.__image_metadata = image_metadata

    def get_message_content(self):
        return self.__message_content

    def is_offensive(self):
        print ('Check for offensive image content')

    def is_valid(self):
        if (self.get_message_content() is None):
            return False
        return True

    def __str__(self):
        # TODO: Override this function to print meaningful debug messages.
        pass


class VoiceMessage(Message):
    def __init__(self):
        super().__init__()

        # Fields related to voice message.
        self.__message_content = None
        self.__voice_duration_in_sec = None
        self.__voice_quality_in_kbps = None
        self.__message_type = Message.MSG_TYPE_VOICE

    def set_message_content(self, voice_message_content: str,
                                  voice_duration: int, voice_quality: int):
        self.__message_content = voice_message_content
        self.__voice_duration_in_sec = voice_duration
        self.__voice_quality_in_kbps = voice_quality

    def __str__(self):
        # TODO: Override this function to print meaningful debug messages.
        pass
