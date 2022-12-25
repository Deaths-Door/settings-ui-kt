package com.deathsdoor.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deathsdoor.myapplication.databinding.ActivityMainBinding
import com.deathsdoor.myapplication.databinding.ActivityMainBinding.inflate
import com.deathsdoor.ui_core.public.SettingBuilder.SwitchSetting
import com.deathsdoor.ui_core.public.createAttributeSet
import com.deathsdoor.ui_core.widgets.Switch
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val parserFactory = XmlPullParserFactory.newInstance()
        val parser = parserFactory.newPullParser()
        parser.setInput(AttributeSetInputStream(attrs), null)
        val xmlBlockParser = XmlBlock.Parser.newInstance(parser)


        binding.root.addView(
            Switch(this,
                SwitchSetting(
                    title = "Switch Setting",
                    shortDescription = "This is a switch setting"
                ).createAttributeSet()
            )
        )
    //    SettingBuilder.show(binding.root.id,R.layout.setting_test,supportFragmentManager)
    }
}