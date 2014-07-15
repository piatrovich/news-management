/**
 * This function loads list of all news using application REST API
 */
function loadAllNews() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management/api/all",
        success: function (data) {
            $.each(data, function (key, value) {
                var article = $("#article-block-template").clone();
                $(article).attr("id", function (i, val) {
                    return val + value["id"];
                });
                addIdToHref(article, ["#article-view", "#article-edit", "#article-delete"], value["id"]);
                $(article).find("#article-title").text(value["title"]);
                changeId(article, "#article-title", value["id"]);
                $(article).find("#article-date").text(new Date(value["date"]).toLocaleDateString());
                changeId(article, "#article-date", value["id"]);
                $(article).find("#article-description").text(value["description"]);
                changeId(article, "#article-description", value["id"]);
                $("#news-body").append(article);
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " Error has occurred");
        },
        dataType: "json"
    });
}

/**
 * This function loads single news for view from API
 */
function loadNewsForView() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/news-management/api/view/" + getIDFromCurrentPageUrl(),
        success: function (data) {
            $("#article-title").text(data["title"]);
            $("#article-date").text(new Date(data["date"]).toLocaleDateString());
            $("#article-description").text(data["description"]);
            $("#article-text").text(data["text"]);
            addIdToHref($("#sidebar"), ["#article-edit", "#article-delete"], data["id"]);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + " Error has occurred");
        },
        dataType: "json"
    });
}

/**
 * Adds custom value to tag id
 *
 * @param article Html element
 * @param id {number} - tag id
 * @param value {string} - appending value
 */
function changeId(article, id, value) {
    $(article).find(id).attr("id", function (i, val) {
        return val + value;
    });
}

/**
 * This function searches number in current pathname
 *
 * @returns {number} - id at the pathname end
 */
function getIDFromCurrentPageUrl() {
    var re = /[0-9]+/;
    return re.exec(window.location.pathname)[0];
}

/**
 * Adds article id to different hrefs
 *
 * @param article Html element
 * @param items Array of tag id's
 * @param id {number} - article id
 */
function addIdToHref(article, items, id) {
    items.forEach(function (item) {
        $(article).find(item).attr("href", function (i, val) {
            return val + "/" + id;
        });
    });
}