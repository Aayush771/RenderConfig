from user_preference import UserPreference


def recover_chat_backup_ios(username):
    print ('Recovering chat from backup')
    # iOS specific code
    # ...
    # iOS specific code


def backup_chat_ios(username, chat):
    print ('Store chat to backup')
    # iOS specific code
    # ...
    # iOS specific code

def change_user_country_ios(username, country):
    # Some iOS specific code goes here.
    # iOS specific code
    # ...
    # iOS specific code

    # Client code doesn't really care about UserPreference functions.
    # It just says update country.
    user_preference = UserPreference(username, user_country=country)
    user_preference.update_user_country()


# This function is called by iOS app eventually.
def change_user_language_ios(username, country, language):
    # Some iOS specific code goes here.
    # iOS specific code
    # ...
    # iOS specific code

    # Client code doesn't really care about UserPreference functions.
    # It just says update language.
    user_preference = UserPreference(username, user_country=country, user_language=language)
    user_preference.update_user_language()
