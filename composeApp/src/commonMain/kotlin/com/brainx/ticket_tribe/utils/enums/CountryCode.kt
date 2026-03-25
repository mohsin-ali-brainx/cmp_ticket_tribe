package com.brainx.ticket_tribe.utils.enums


enum class CountryCode(
    val countryName: String,
    val code: String,
    val dialCode: String,
    val flag: String,
    val minLength: Int,
    val maxLength: Int
) {

    US("United States", "US", "+1", "🇺🇸", 10, 10),
    GB("United Kingdom", "GB", "+44", "🇬🇧", 10, 10),
    IN("India", "IN", "+91", "🇮🇳", 10, 10),
    CA("Canada", "CA", "+1", "🇨🇦", 10, 10),
    EG("Egypt", "EG", "+20", "🇪🇬", 10, 11),
    ZA("South Africa", "ZA", "+27", "🇿🇦", 9, 9),
    GR("Greece", "GR", "+30", "🇬🇷", 10, 10),
    NL("Netherlands", "NL", "+31", "🇳🇱", 9, 9),
    BE("Belgium", "BE", "+32", "🇧🇪", 8, 9),
    FR("France", "FR", "+33", "🇫🇷", 9, 9),
    ES("Spain", "ES", "+34", "🇪🇸", 9, 9),
    IT("Italy", "IT", "+39", "🇮🇹", 9, 10),
    CH("Switzerland", "CH", "+41", "🇨🇭", 9, 9),
    AT("Austria", "AT", "+43", "🇦🇹", 10, 11),
    DK("Denmark", "DK", "+45", "🇩🇰", 8, 8),
    NO("Norway", "NO", "+47", "🇳🇴", 8, 8),
    PL("Poland", "PL", "+48", "🇵🇱", 9, 9),
    DE("Germany", "DE", "+49", "🇩🇪", 10, 11),
    MX("Mexico", "MX", "+52", "🇲🇽", 10, 13),
    AR("Argentina", "AR", "+54", "🇦🇷", 10, 13),
    BR("Brazil", "BR", "+55", "🇧🇷", 10, 11),
    MY("Malaysia", "MY", "+60", "🇲🇾", 9, 10),
    AU("Australia", "AU", "+61", "🇦🇺", 9, 9),
    ID("Indonesia", "ID", "+62", "🇮🇩", 9, 12),
    PH("Philippines", "PH", "+63", "🇵🇭", 10, 10),
    NZ("New Zealand", "NZ", "+64", "🇳🇿", 8, 9),
    SG("Singapore", "SG", "+65", "🇸🇬", 8, 8),
    TH("Thailand", "TH", "+66", "🇹🇭", 9, 9),
    RU("Russia", "RU", "+7", "🇷🇺", 10, 10),
    JP("Japan", "JP", "+81", "🇯🇵", 10, 10),
    KR("South Korea", "KR", "+82", "🇰🇷", 9, 11),
    VN("Vietnam", "VN", "+84", "🇻🇳", 9, 10),
    CN("China", "CN", "+86", "🇨🇳", 11, 11),
    AF("Afghanistan", "AF", "+93", "🇦🇫", 9, 9),
    IR("Iran", "IR", "+98", "🇮🇷", 10, 10),
    TR("Turkey", "TR", "+90", "🇹🇷", 10, 11),
    PK("Pakistan", "PK", "+92", "🇵🇰", 10, 10),
    BD("Bangladesh", "BD", "+880", "🇧🇩", 10, 10),
    HK("Hong Kong", "HK", "+852", "🇭🇰", 8, 8),
    IQ("Iraq", "IQ", "+964", "🇮🇶", 10, 10),
    SA("Saudi Arabia", "SA", "+966", "🇸🇦", 9, 9),
    AE("United Arab Emirates", "AE", "+971", "🇦🇪", 9, 9),
    IE("Ireland", "IE", "+353", "🇮🇪", 9, 9),
    UA("Ukraine", "UA", "+380", "🇺🇦", 9, 9),
    PT("Portugal", "PT", "+351", "🇵🇹", 9, 9),
    AL("Albania", "AL", "+355", "🇦🇱", 9, 9),
    DZ("Algeria", "DZ", "+213", "🇩🇿", 9, 9);

    companion object {

        val sortedByDialCode: List<CountryCode>
            get() = entries.sortedBy {
                it.dialCode.removePrefix("+").toInt()
            }
    }
}