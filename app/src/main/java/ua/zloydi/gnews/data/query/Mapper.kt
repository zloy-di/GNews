package ua.zloydi.gnews.data.query

import java.text.SimpleDateFormat
import java.util.*

private const val QUERY = "q"
private const val FROM = "from"
private const val TO = "to"
private const val SEARCH_IN = "in"
private const val SORT = "sortby"

private fun Date.toQuery(simpleDateFormat: SimpleDateFormat) : String{
	return simpleDateFormat.format(this)
}

fun List<SearchIn>.toQuery(): String = buildString {
	this@toQuery.forEach {
		append(it)
		append(',')
	}
	deleteCharAt(lastIndex)
}

fun Filter.toQueryMap(): Map<String, String>{
	val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
	return mapOf(
		FROM to from.toQuery(dateFormat),
		TO to to.toQuery(dateFormat),
		SEARCH_IN to searchIn.toQuery()
	)
}

fun Query.toQueryMap(): Map<String, String> = buildMap {
	put(QUERY, q)
	if (filter != null) putAll(filter.toQueryMap())
	put(SORT, sort.queryName)
}