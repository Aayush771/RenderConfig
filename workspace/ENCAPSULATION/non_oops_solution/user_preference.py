import json

# Utilities to store user preferences in a file.

# For the purposes of the exercise, you can ignore this part of the code.

# Store the preferences in a file. It's a simplistic world remember?
def update_user_country(user_name, user_country):
    user_preference = {}

    # Load the existing user preferences data from file.
    user_preferences = read_preferences_from_file()

    if (user_preferences is None):
        user_preferences = []

    # Find the specific user entry if it exists.
    user_found = False
    for tmp_user_preference in user_preferences:
        if tmp_user_preference['user_name'] == user_name:
            # Found an entry for user, update it.
            print ('User preferences exists for: ' + user_name)
            user_found = True
            user_preference = tmp_user_preference
            break;

    # Add a new entry if user is not found.
    if user_found is False:
        user_preferences.append(user_preference)

    user_preference['user_name'] = user_name
    user_preference['user_country'] = user_country

    with open('non_oops_solution/user_preferences.json', 'w') as outfile:
        json.dump(user_preferences, outfile)
        outfile.write('\n')


# Store the preferences in a file. It's a simplistic world remember?
def update_user_language(user_name, user_language):
    user_preference = {}

    # Load the existing user preferences data from file.
    user_preferences = read_preferences_from_file()

    if (user_preferences is None):
        user_preferences = []

    # Find the specific user entry if it exists.
    user_found = False
    for tmp_user_preference in user_preferences:
        if tmp_user_preference['user_name'] == user_name:
            # Found an entry for user, update it.
            print ('User preferences exists for: ' + user_name)
            user_found = True
            user_preference = tmp_user_preference
            break;

    # Add a new entry if user is not found.
    if user_found is False:
        user_preferences.append(user_preference)

    user_preference['user_name'] = user_name
    user_preference['user_language'] = user_language

    with open('non_oops_solution/user_preferences.json', 'w') as outfile:
        json.dump(user_preferences, outfile)
        outfile.write('\n')

# Read the preferences for a user from a file.
def read_preferences_from_file():
    with open('non_oops_solution/user_preferences.json') as inputfile:
        try:
            user_preferences = json.load(inputfile)
            return user_preferences
        except:
            print('Seems the file is empty. Not a problem')
