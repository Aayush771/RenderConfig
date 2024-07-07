from user_preference import update_user_language, update_user_country


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
    update_user_country(user_name, user_country)


# This function is called by Android app eventually.
def change_user_language_android(user_name, user_country, user_language):
    # Some Android specific code goes here.
    # Android specific code
    # ...
    # ...
    # Android specific code

    # Do some basic validation of whether this language is spoken in the country.
    # It is given that users in USA can speak only English or Spanish,
    # and Indians can speak only English or Hindi.
    if (user_country == 'COUNTRY_INDIA'
            and (user_language == 'LANGUAGE_HINDI' or user_language == 'LANGUAGE_ENGLISH')
        or user_country == 'COUNTRY_USA'
            and (user_language == 'LANGUAGE_SPANISH' or user_language == 'LANGUAGE_ENGLISH')):
        update_user_language(user_name, user_language)
    else:
        raise Exception('Invalid country/language combination')



