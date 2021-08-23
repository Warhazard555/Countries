package com.example.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.dto.CountryItemDto
import com.example.domain.dto.LanguageDto
import com.example.task1.convertToList

@Entity(tableName = "Country")
data class TableModel(

    @PrimaryKey @ColumnInfo
    val name: String,

    @ColumnInfo
    val capital: String,

    @ColumnInfo
    val area: Float,

    @ColumnInfo
    val language: String,

    @ColumnInfo
    val population: Int

) {
    fun convertTableModelToDto(): CountryItemDto {

        var name: String = ""
        var capital: String = ""
        var area: Float = 0F
        var flag: String = ""
        var languages: List<LanguageDto> = arrayListOf()
        var latlng: List<Double> = arrayListOf(1.0, 1.0)
        var population: Int = 0

        this.name.let { name = it }
        this.capital.let {
            capital = it
        }
        this.area.let {
            area = it
        }
        this.population.let {
            population = it
        }
        this.language.let { languages.convertToList() }

        return CountryItemDto(name, capital, area, flag, languages, latlng, population)
    }
}

fun MutableList<TableModel>.convertToDTO(): MutableList<CountryItemDto> {
    val list = mutableListOf<CountryItemDto>()
    for (item in this) {
        list.add(item.convertTableModelToDto())
    }
    return list
}
fun List<CountryItemDto>.convertToTableModel(): MutableList<TableModel>{
    val list = mutableListOf<TableModel>()
    for (item in this){
        list.add(item.convertDtoToTableModel())
    }
    return list
}

fun CountryItemDto.convertDtoToTableModel(): TableModel {

    var name: String = ""
    var capital: String = ""
    var area: Float = 0F
    var language: String = ""
    var population: Int = 0

    this.name.let { name = it }
    this.capital.let {
        capital = it
    }
    this.area.let {
        area = it
    }
    this.population.let {
        population = it
    }
    this.languages.let {language = it.convertToList()}

    return TableModel(name, capital, area,language, population)
}



