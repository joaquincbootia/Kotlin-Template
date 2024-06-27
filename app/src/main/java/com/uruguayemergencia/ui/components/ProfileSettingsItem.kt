package com.uruguayemergencia.ui.components

import com.uruguayemergencia.R

enum class ProfileSettingsItem(
    val icon: Int,
    val labelText: Int,
    val isRed: Boolean,
    val onClick: () -> Unit
) {
    INVITE(R.drawable.invite, R.string.invite, false, { }),
    DELETE(R.drawable.delete_account, R.string.delete_account, false, { }),
    LOGOUT(R.drawable.log_out, R.string.log_out, true, { })
}
