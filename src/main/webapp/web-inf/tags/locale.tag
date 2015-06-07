<select id="locale" name="locale">

    <option value="en" ${pageContext.request.locale.language=='en' ? 'selected="selected"':''}>en</option>
    <option value="ru" ${pageContext.request.locale.language=='ru' ? 'selected="selected"':''}>ru</option>
</select>