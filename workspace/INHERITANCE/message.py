# Message class contains all message related variables/functions.
class Message:
    # Python doesn't support private variables like Java/C++
    # Convention is that if you prefix a variable with _var or __var, it is considered private.
    def __init__(self):
        self.__message_id = None
        self.__sender = None
        self.__receiver = None
        self.__message_content = None

        # More common fields, can be extended to many more.
        self.__delivery_status = None
        self.__return_receipt_required = None

    def set_sender(self, sender):
        self.__sender = sender

    def set_receiver(self, receiver):
        self.__receiver = receiver

    def set_message_content(self, message_content: str):
        self.__message_content = message_content

    def get_sender(self):
        return self.__sender

    def get_receiver(self):
        return self.__receiver

    def get_message_content(self):
        return self.__message_content

    def get_message_size(self):
        return len(self.__message_content)


