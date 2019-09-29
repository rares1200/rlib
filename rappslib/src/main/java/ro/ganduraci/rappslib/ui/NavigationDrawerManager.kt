package ro.ganduraci.rappslib.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import ro.ganduraci.rappslib.R
import ro.ganduraci.rappslib.utils.ImageUtils

object NavigationDrawerManager {

    class HeaderBuilder(inflater: LayoutInflater) {

        companion object {
            const val INVALID_RES_ID = -1
        }

        private val view = inflater.inflate(R.layout.nav_header, null)
        private var height: Int = view.context.resources.getDimension(R.dimen.nav_header_height).toInt()
        private var backgroundResId: Int = INVALID_RES_ID
        private var userImageUrl = ""
        private var userImagePlaceholder: Int = R.drawable.ic_user
        private var userImageResId: Int = INVALID_RES_ID
        private var textRow1 = ""
        private var textRow2 = ""
        private var textRow1ColorResId: Int = INVALID_RES_ID
        private var textRow2ColorResId: Int = INVALID_RES_ID

        fun height(height: Int): HeaderBuilder {
            this.height = height
            return this
        }

        fun backgroundResId(backgroundResId: Int): HeaderBuilder {
            this.backgroundResId = backgroundResId
            return this
        }

        fun userImageUrl(userImageUrl: String): HeaderBuilder {
            this.userImageUrl = userImageUrl
            return this
        }

        fun userImagePlaceholder(userImagePlaceholderResId: Int): HeaderBuilder {
            this.userImagePlaceholder = userImagePlaceholderResId
            return this
        }

        fun userImageResId(userImageResId: Int): HeaderBuilder {
            this.userImageResId = userImageResId
            return this
        }

        fun textRow1(textRow1: String, textColor:Int = INVALID_RES_ID): HeaderBuilder {
            this.textRow1 = textRow1
            this.textRow1ColorResId = textColor
            return this
        }

        fun textRow2(textRow2: String, textColor: Int = INVALID_RES_ID): HeaderBuilder {
            this.textRow2 = textRow2
            this.textRow2ColorResId = textColor
            return this
        }

        fun build(): View {
            val ctx = view.context
            view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
            if (backgroundResId != INVALID_RES_ID) {
                view.background = ContextCompat.getDrawable(ctx, backgroundResId)
            }
            if (userImageUrl.isNotEmpty()) {
                ImageUtils.loadCircularImageFromUrl(view.findViewById(R.id.img_profile) as ImageView,
                    userImageUrl,
                    userImagePlaceholder)
            } else if (userImageResId != INVALID_RES_ID) {
                ImageUtils.loadCircularImageFromResource(view.findViewById(R.id.img_profile) as ImageView,
                    userImageResId)
            }
            if (textRow1.isNotEmpty()) {
                val tvRow1: TextView = view.findViewById(R.id.row_1)
                tvRow1.text = textRow1
                if (textRow1ColorResId != INVALID_RES_ID) {
                    tvRow1.setTextColor(ContextCompat.getColor(ctx, textRow1ColorResId))
                }
            }
            if (textRow2.isNotEmpty()) {
                val tvRow2: TextView = view.findViewById(R.id.row_2)
                tvRow2.text = textRow2
                if (textRow2ColorResId != INVALID_RES_ID) {
                    tvRow2.setTextColor(ContextCompat.getColor(ctx, textRow2ColorResId))
                }
            }
            return view
        }
    }

}