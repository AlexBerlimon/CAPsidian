sap.ui.require(
    [
        'sap/fe/test/JourneyRunner',
        'linksservice/test/integration/FirstJourney',
		'linksservice/test/integration/pages/LinksList',
		'linksservice/test/integration/pages/LinksObjectPage'
    ],
    function(JourneyRunner, opaJourney, LinksList, LinksObjectPage) {
        'use strict';
        var JourneyRunner = new JourneyRunner({
            // start index.html in web folder
            launchUrl: sap.ui.require.toUrl('linksservice') + '/index.html'
        });

       
        JourneyRunner.run(
            {
                pages: { 
					onTheLinksList: LinksList,
					onTheLinksObjectPage: LinksObjectPage
                }
            },
            opaJourney.run
        );
    }
);