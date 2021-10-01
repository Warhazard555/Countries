package com.example.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.dto.CountryItemDto
import com.example.domain.dto.LanguageDto

@Entity(tableName = "Country")
data class TableModel(

    @PrimaryKey @ColumnInfo
    val name: String,

    @ColumnInfo
    val capital: String,

    @ColumnInfo
    val area: Float,

    @ColumnInfo
    val flag: String,

    @ColumnInfo
    val population: Int,

    @ColumnInfo
    val currentDistance: Int


) {
    fun convertTableModelToDto(): CountryItemDto {

        var name: String = ""
        var capital: String = ""
        var area: Float = 0F
        var flag: String = ""
        var languages: List<LanguageDto> = arrayListOf()
        var latlng: MutableList<Double> = arrayListOf(1.0, 1.0)
        var population: Int = 0
        var currentDistance: Int = 0

        this.name.let { name = it }
        this.capital.let {
            capital = it
        }
        this.area.let {
            area = it
        }
        this.flag.let {
            flag = it
        }
        this.population.let {
            population = it
        }

        this.currentDistance.let { currentDistance = it }

        return CountryItemDto(
            name,
            capital,
            area,
            flag,
            languages,
            latlng,
            population,
            currentDistance
        )
    }
}

fun MutableList<TableModel>.convertToDTO(): MutableList<CountryItemDto> {
    val list = mutableListOf<CountryItemDto>()
    for (item in this) {
        list.add(item.convertTableModelToDto())
    }
    return list
}

fun List<CountryItemDto>.convertToTableModel(): MutableList<TableModel> {
    val list = mutableListOf<TableModel>()
    for (item in this) {
        list.add(item.convertDtoToTableModel())
    }
    return list
}

fun CountryItemDto.convertDtoToTableModel(): TableModel {

    var name: String = ""
    var capital: String = ""
    var area: Float = 0F
    var flag: String = ""
    var population: Int = 0
    var currentDistance: Int = 0

    this.name.let { name = it }
    this.capital.let {
        capital = it
    }

    this.area.let {
        area = it
    }

    this.flags.let { flag = it }

    this.population.let {
        population = it
    }

    this.currentDistance.let { currentDistance = it }

    return TableModel(name, capital, area, flag, population, currentDistance)
}



