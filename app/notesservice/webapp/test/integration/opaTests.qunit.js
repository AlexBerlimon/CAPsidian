sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'notesservice/test/integration/FirstJourney',
		'notesservice/test/integration/pages/NotesList',
		'notesservice/test/integration/pages/NotesObjectPage'
    ],
    function(JourneyRunner, opaJourney, NotesList, NotesObjectPage) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('notesservice') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onTheNotesList: NotesList,
					onTheNotesObjectPage: NotesObjectPage
                }
            },
            opaJourney.run
        );
    }
);