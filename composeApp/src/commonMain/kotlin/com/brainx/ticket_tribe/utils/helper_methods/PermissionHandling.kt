package com.brainx.ticket_tribe.utils.helper_methods

import com.mohamedrejeb.calf.permissions.ExperimentalPermissionsApi
import com.mohamedrejeb.calf.permissions.PermissionState
import com.mohamedrejeb.calf.permissions.isDenied
import com.mohamedrejeb.calf.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
fun requestPermission(permissionState: PermissionState){
    permissionState.apply {
        if (status.isDenied){
            if (status.shouldShowRationale){
                // show move to setting dialog and on confirm openAppSettings
                openAppSettings()
            }else{
                launchPermissionRequest()
            }
        }
    }
}