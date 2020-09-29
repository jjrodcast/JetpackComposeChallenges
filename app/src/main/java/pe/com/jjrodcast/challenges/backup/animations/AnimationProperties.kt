package pe.com.jjrodcast.challenges.backup.animations

import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey


// Title Properties
internal val propTitleOpacity = FloatPropKey("titleOpacity")
internal val propTitleOffset = DpPropKey("titleOffset")

// Description Properties
internal val propDescInitialOpacity = FloatPropKey("initialOpacity")
internal val propDescInitialOffset = DpPropKey("initialOffset")
internal val propDescTwoInitialOpacity = FloatPropKey("initialTwoOpacity")
internal val propDescTwoInitialOffset = DpPropKey("initialTwoOffset")
internal val propDescBackupOpacity = FloatPropKey("backupOpacity")

// Cloud Properties
internal val propCloudWidth = FloatPropKey("cloudWidth")
internal val propCloudRadius = FloatPropKey("cloudRadius")
internal val propCloudHeight = FloatPropKey("cloudHeight")
internal val propBubblesOffset = FloatPropKey("bubblesOffset")

// Circle & Check Properties
internal val propCircleAngle = FloatPropKey("circleAngle")

// Completed Properties
internal val propTextCompletedOffset = DpPropKey("completedOffset")
internal val propTextCompletedOpacity = FloatPropKey("completedOpacity")

