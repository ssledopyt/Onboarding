package ui.onboarding.data.items.types

import R

sealed class ServiceType(val icon: Int, val title: Int)
{
    object TakeAway:
        ServiceType(R.drawable.ic_service_takeaway, R.string.checkbox_takeaway)

    object InInstitution:
        ServiceType(R.drawable.ic_service_in_institution, R.string.checkbox_in)

    object Delivery:
        ServiceType(R.drawable.ic_service_delivery, R.string.checkbox_delivery)
}
