from android_client_handler import change_user_language_android, change_user_country_android
from ios_client_handler import change_user_language_ios, change_user_country_ios

if __name__== "__main__":
    # Call the android/iOS handler code to change user language/country etc.
    # In real world, these functions will be called from your Flask or Django server methods.
    # Don't worry about them for now - let's live in a simple world :)

    change_user_country_android('luis', 'COUNTRY_USA')
    change_user_country_ios('budha', 'COUNTRY_INDIA')

    change_user_language_android('luis', 'COUNTRY_USA', 'LANGUAGE_SPANISH')
    change_user_language_ios('budha', 'COUNTRY_INDIA', 'LANGUAGE_HINDI')
    change_user_language_android('akash', 'COUNTRY_INDIA', 'LANGUAGE_ENGLISH')
