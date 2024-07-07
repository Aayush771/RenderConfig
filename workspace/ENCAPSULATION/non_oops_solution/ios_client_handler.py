from user_preference import update_user_language, update_user_country


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
    update_user_country(username, country)


# This function is called by iOS app eventually.
def change_user_language_ios(username, country, language):
    # Some iOS specific code goes here.
    # iOS specific code
    # ...
    # iOS specific code

    # Do some basic validation of whether this language is spoken in the country.
    # It is given that users in USA can speak only English or Spanish,
    # and Indians can speak only English or Hindi.
    if (country == 'COUNTRY_USA' and (language == 'LANGUAGE_SPANISH' or language == 'LANGUAGE_ENGLISH')
        or country == 'COUNTRY_INDIA' and (language == 'LANGUAGE_HINDI' or language == 'LANGUAGE_ENGLISH')):
        update_user_language(username, language)
    else:
        raise Exception('Invalid country/language combination')
