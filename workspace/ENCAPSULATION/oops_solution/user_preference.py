import json

# UserPreference class that has variables and functions that help maintain
# user preferences in a clean way.
class UserPreference:
    # Python doesn't support private variables like Java/C++
    # Convention is that if you prefix a variable with _var or __var, it is considered private.
    def __init__(self):
        self.__user_name = None
        self.__user_country = None
        self.__user_language = None

    def __init__(self, user_name, user_country=None, user_language=None):
        self.__user_name = user_name
        self.__user_country = user_country
        self.__user_language = user_language

    # All the validations related to user language is done in this single function.
    # The callers of this function don't have to worry about validations.
    def update_user_language(self):
        # It is given that users in USA can speak only English or Spanish,
        # and Indians can speak only English or Hindi.
        if ((self.__user_country == 'COUNTRY_USA'
             and (self.__user_language == 'LANGUAGE_SPANISH' or self.__user_language == 'LANGUAGE_ENGLISH'))
                or (self.__user_country == 'COUNTRY_INDIA'
                    and (self.__user_language == 'LANGUAGE_HINDI' or self.__user_language == 'LANGUAGE_ENGLISH'))):
            self.__user_country = None;  # Don't want to update country as a part of this call.
            self._update_user_preferences_in_file();
        else:
            raise Exception('Invalid country/language combination')


    def update_user_country(self):
        self._update_user_preferences_in_file();


    # For the purposes of the exercise, you can ignore this part of the code.

    # Store the preferences in a file. It's a simplistic world remember?
    def _update_user_preferences_in_file(self):
        user_preference = {}

        # Load the existing user preferences data from file.
        user_preferences = self._read_preferences_from_file()

        if (user_preferences is None):
            user_preferences = []

        # Find the specific user entry if it exists.
        user_found = False
        for tmp_user_preference in user_preferences:
            if tmp_user_preference['user_name'] == self.__user_name:
                # Found an entry for user, update it.
                print ('User preferences exists for: ' + self.__user_name)
                user_found = True
                user_preference = tmp_user_preference
                break;

        # Add a new entry if user is not found.
        if user_found is False:
            user_preferences.append(user_preference)

        user_preference['user_name'] = self.__user_name

        # If country has to be updated.
        if (self.__user_country is not None):
            user_preference['user_country'] = self.__user_country

        # If language has to be updated.
        if (self.__user_language is not None):
            user_preference['user_language'] = self.__user_language

        with open('oops_solution/user_preferences.json', 'w') as outfile:
            json.dump(user_preferences, outfile)
            outfile.write('\n')

    # Read the preferences for a user from a file.
    def _read_preferences_from_file(self):
        with open('oops_solution/user_preferences.json') as inputfile:
            try:
                user_preferences = json.load(inputfile)
                return user_preferences
            except:
                print('Seems the file is empty. Not a problem')