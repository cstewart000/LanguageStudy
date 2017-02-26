# LanguageStudy
Android App for Language Study

Just a simple app

Applictation uses google Speech-To-Text (STT) and Text-To-Speech (TTS) APIs to communicate with the user.

This application currently supports **FRENCH** :fr: only

The words employed are generated using a dictionary of French words with the International Phonetic Alphabet (IPA) value against each word

a Compactus is used that scores words from most frequent to least frequent in the language

-- A breif explaination of IPA --

IPA - is the international system for representation of verbal sounds (termed phonemes). The system is developed mostly to cater for latin based languages.

The phonetic alphabet is used to discribe the pronunciation of a word where the spelling does not give a good indication.

The native language of a language learner will have some common and some unique phonemes with the target language. These disimilar phonemes should be the focus of the learner as they will be the hardest to aquire and will impede fluid speech.

Notation - IPA uses forward ticks ('/') to indicate the limits of a string of phonemes

An example word:

Serrurerie /seʀyʀʀi/ (source: http://www.thelocal.fr/20150206/french-language-pronunciation-words--top-ten)

To pronounce this word well, a language learner must master the pronunciation of each of these sounds and combine them quickly and effortlessly.

/s/
/e/
/ʀ/
/y/
/ʀ/
/ʀ/
/i/

/s/,/e/,/i/ and /y/ are all common in English. but /ʀ/ is not 

![phonemic chart](https://www.englishclub.com/images/pronunciation/Phonemic-Chart.jpg "English Phonemic Chart")


we have /r/ in english.

-- How the app works --

Retrieveing a word from the database, the word is displayed on screen and the user can press the 'play' button to hear the model pronunciation which is provided by the native Text To Speech engine.

![app screenshot](https://drive.google.com/open?id=0B8_hXvCtBTMMT3FrSjNHVnM1d25EQmtacWRTNWRqbmo0M1Nn "screenshot of the applicaiton")


The user then has the opportunity to record their attempt at pronouncing the word.

The application uses the google speech to text intent to record the users speech.

The speech to text intent passes back possible words that the user may have said:

[serrurie, ferrari, serie .. etc]

As the word matches the target, the user recieves 100% score in this case.

If the user pronounces incorrectly and the correct word is not returned, the application determines which phonemes were missing from the recording.

Incorrectly pronounced words/phonemes are to be stored in a database so that the application can respond uniquely to the user based on ability.

--- Statistics objectives

- build a model of the users ability based on performance to rank, track progress and modify the difficulty of the applicaiton

- predict what words will be difficult for the speaker to pronounce based on their history

- profile the users strengths/weaknesses so that the application can adapt to train the user.



