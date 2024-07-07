from user_preference import UserPreference


def recover_chat_backup_android(username):
    print('Recovering chat from backup')
    # Android specific code
    # ...
    # Android specific code


def backup_chat_Android(username, chat):
    print('Store chat to backup')
    # Android specific code
    # ...
    # Android specific code

def change_user_country_android(user_name, user_country):
    # Some Android specific code goes here.
    # Android specific code
    # ...
    # ...
    # Android specific code

    # Client code doesn't really care about UserPreference functions.
    # It just says update country.
    user_preference = UserPreference(user_name, user_country=user_country)
    user_preference.update_user_country()


# This function is called by Android app eventually.
def change_user_language_android(user_name, user_country, user_language):
    # Some Android specific code goes here.
    # Android specific code
    # ...
    # ...
    # Android specific code

    # Client code doesn't really care about UserPreference functions.
    # It just says update language.
    user_preference = UserPreference(user_name, user_country=user_country, user_language=user_language)
    user_preference.update_user_language()
