package ui.onboarding.data.items.types

import R

sealed class InstitutionType(val icon: Int, val title: Int)
{
    object Restaurant:
        InstitutionType(R.drawable.ic_restaurant, R.string.checkbox_restaurant)

    object Bar:
        InstitutionType(R.drawable.ic_bar, R.string.checkbox_bar)

    object Cafe:
        InstitutionType(R.drawable.ic_cafe, R.string.checkbox_cafe)

    object Canteen:
        InstitutionType(R.drawable.ic_canteen, R.string.checkbox_canteen)

    object CoffeeHouse:
        InstitutionType(R.drawable.ic_coffee_house, R.string.checkbox_coffee_shop)

    object Cookery:
        InstitutionType(R.drawable.ic_cookery, R.string.checkbox_cookery)

    object Other:
        InstitutionType(R.drawable.ic_other, R.string.checkbox_other)
}
