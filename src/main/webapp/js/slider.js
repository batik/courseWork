$(function () {
    $('#slider').slidertron({
        viewerSelector: '.viewer',
        reelSelector: '.viewer .reel',
        slidesSelector: '.viewer .reel .slide',
        advanceDelay: 3000,
        speed: 'slow',
        navPreviousSelector: '.previous-button',
        navNextSelector: '.next-button',
        slideLinkSelector: '.link'
    });
});