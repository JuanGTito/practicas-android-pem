package com.juantito.programacionequiposmoviles

interface Exportable {
    val format: String
    fun export (data: String): String
    fun validate (data: String): Boolean
}
class PdfExporter : Exportable {
    override val format = "PDF"
    override fun export (data: String) = "[$format] Generating PDF with: $data"
    override fun validate (data: String) = data.isNotBlank()
}
class CsvExporter : Exportable {
    override val format = "CSV"
    override fun export (data: String) = "[$format] Separating by commas: $data"
    override fun validate (data: String) = data.contains(",")
}
class JsonExporter : Exportable {
    override val format = "JSON"
    override fun export (data: String) = "[$format] Serializing: $data"
    override fun validate (data: String) = data.isNotBlank() && data.length <= 1000
}
