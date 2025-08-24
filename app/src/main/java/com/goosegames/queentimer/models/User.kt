package com.goosegames.queentimer.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
	val jsonapi: Jsonapi? = null,
	val data: Data? = null,
	val meta: Meta? = null,
	val links: Links? = null,
	val included: List<IncludedItem?>? = null
)

@Serializable
data class SecurityRoles(
	val data: List<String>? = null
)

@Serializable
data class Machines(
	val data: List<String>? = null
)

@Serializable
data class Accounts(
	val data: List<DataItem?>? = null
)

@Serializable
data class IncludedItem(
	val relationships: Relationships? = null,
	val attributes: Attributes? = null,
	val links: Links? = null,
	val id: String? = null,
	val type: String? = null
)

@Serializable
data class Meta(
	val authToken: String? = null
)

@Serializable
data class EmailAddressType(
	val data: Data? = null
)

@Serializable
data class Roles(
	val data: List<DataItem?>? = null
)

@Serializable
data class Relationships(
	val securityRoles: SecurityRoles? = null,
	val updatedBy: UpdatedBy? = null,
	val emailAddresses: EmailAddresses? = null,
	val geoBoundaryUnreadMailboxNotifications: GeoBoundaryUnreadMailboxNotifications? = null,
	val createdBy: CreatedBy? = null,
	val organization: Organization? = null,
	val roles: Roles? = null,
	val customerGeoBoundaries: CustomerGeoBoundaries? = null,
	val accounts: Accounts? = null,
	val machines: Machines? = null,
	val userRole: UserRole? = null,
	val geoBoundary: GeoBoundary? = null,
	val subAccounts: SubAccounts? = null,
	val user: User? = null,
	val userAccount: UserAccount? = null,
	val geoBoundaryType: GeoBoundaryType? = null,
	val emailAddressType: EmailAddressType? = null,
	val organizationUser: OrganizationUser? = null,
	val organizationUserRole: OrganizationUserRole? = null,
	val organizationUserRoleType: OrganizationUserRoleType? = null,
	val userRoleType: UserRoleType? = null,
	val roleType: RoleType? = null
)

@Serializable
data class CustomerGeoBoundaries(
	val data: List<DataItem?>? = null
)

@Serializable
data class DataItem(
	val id: String? = null,
	val type: String? = null
)

@Serializable
data class Organization(
	val data: Data? = null
)

@Serializable
data class EmailAddresses(
	val data: List<DataItem?>? = null
)

@Serializable
data class UserAccount(
	val data: Data? = null
)

@Serializable
data class GeoBoundary(
	val data: Data? = null
)

@Serializable
data class CreatedBy(
	val data: Data? = null
)

@Serializable
data class OrganizationUser(
	val data: Data? = null
)

@Serializable
data class Data(
	val relationships: Relationships? = null,
	val attributes: Attributes? = null,
	val links: Links? = null,
	val id: String? = null,
	val type: String? = null
)

@Serializable
data class OrganizationUserRoleType(
	val data: Data? = null
)

@Serializable
data class Links(
	val self: String? = null
)

@Serializable
data class UserRoleType(
	val data: Data? = null
)

@Serializable
data class GeoBoundaryType(
	val data: Data? = null
)

@Serializable
data class UserRole(
	val data: Data? = null
)

@Serializable
data class Attributes(
	val lastName: String? = null,
	val relationshipType: String? = null,
	val universalOptOutSms: Boolean? = null,
	val userIsHidden: Boolean? = null,
	val hasAcceptedTOS: Boolean? = null,
	val userGuid: String? = null,
	val isActive: Boolean? = null,
	val locale: String? = null,
	val userName: String? = null,
	val isEmailVerified: Boolean? = null,
	val marketingOptIn: Boolean? = null,
	val firstName: String? = null,
	val createdAt: String? = null,
	val userRelationshipIsActive: Boolean? = null,
	val loginIsActive: Boolean? = null,
	val fbToken: String? = null,
	val hasVerifiedPhoneNumber: Boolean? = null,
	val universalOptOutEmail: Boolean? = null,
	val updatedAt: String? = null,
	val organizationName: String? = null,
	val pin: String? = null,
	val geoBoundaryName: String? = null,
	val isCustomerPreferredGeoBoundary: Boolean? = null,
	val autoReplenishmentEnabled: Boolean? = null,
	val replenishmentMinimumValueAmount: Int? = null,
	val replenishmentValueAmount: Int? = null,
	val appliedAmountIsRewardsCredit: Boolean? = null,
	val balance: Int? = null,
	val promotionalCredit: Int? = null,
	val rewardsPoints: Int? = null,
	val emailAddress: String? = null,
	val isVerified: Boolean? = null,
	val userLastName: String? = null,
	val userFirstName: String? = null,
	val typeName: String? = null,
	val organizationIsActive: Boolean? = null,
	val updatedBy: String? = null,
	val userRoleIsActive: Boolean? = null,
	val createdBy: String? = null,
	val organizationAlgoliaSecuredApiKey: String? = null,
	val weightUnitLookupKey: String? = null,
	val isAtrium: Boolean? = null,
	val isIncludedLaundry: Boolean? = null,
	val isMeteredLaundry: Boolean? = null,
	val canSms: Boolean? = null,
	val currencyName: String? = null,
	val isoCountryName: String? = null,
	val countryCode: String? = null,
	val isoCountryCode: String? = null,
	val countryName: String? = null,
	val brandName: String? = null,
	val symbol: String? = null,
	val divisor: Int? = null,
	val decimalPlaces: Int? = null,
	val iso4217Id: String? = null,
	val decimalSeparator: String? = null,
	val iso4217Code: String? = null,
	val thousandsSeparator: String? = null
)

@Serializable
data class Jsonapi(
	val version: String? = null
)

@Serializable
data class UpdatedBy(
	val data: Data? = null
)

@Serializable
data class OrganizationUserRole(
	val data: Data? = null
)

@Serializable
data class SubAccounts(
	val data: List<DataItem?>? = null
)

@Serializable
data class GeoBoundaryUnreadMailboxNotifications(
	val data: List<String>? = null
)

@Serializable
data class RoleType(
	val data: Data? = null
)

