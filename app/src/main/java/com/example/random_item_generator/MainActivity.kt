package com.example.random_item_generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.view.View
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        output = findViewById(R.id.output)
    }
    private lateinit var output: TextView
    fun generate(view:View){
        val minPhoneNum:TextView = findViewById(R.id.minNum)
        val maxPhoneNum:TextView = findViewById(R.id.maxNum)
        val generator = PhoneGroupGenerator(minPhoneNum.toString().toInt(), maxPhoneNum.toString().toInt())
        generator.generate()
        write(generator.list())
    }
    private fun write(list: MutableList<Phone>){
        var string = ""
        for (i in list.indices) {
            string += "Телефон $i:\n\tПроцессор: ${list[i].retCpu()}\n\tОЗУ: ${list[i].retRam()}\n\tЭкран: ${list[i].retScreen()}\n\t"
            string += if (list[i].retTouchID())
                "Сканер отпечатков пальцев: есть\n"
            else
                "Сканер отпечатков пальцев: нет\n"
        }
        output.text = string
    }
}

class PhoneGroupGenerator(minPhoneNum: Int, maxPhoneNum: Int){
    private val num = Random.nextInt(minPhoneNum, maxPhoneNum)
    private var list: MutableList<Phone> = mutableListOf()

    fun generate(){
        val cpu = arrayOf("Qualcomm", "Samsung", "Huawei", "MediaTek", "Apple")
        for(i in 1..num){
            val ram = Random.nextInt(1,8) * 2
            val screen: Double = Random.nextInt(4, 7).toDouble() + Random.nextInt(1,2).toDouble()/2
            val touchID = Random.nextBoolean()
            list.add(Phone(cpu[Random.nextInt(5) - 1], ram, screen, touchID))
        }
    }

    fun list(): MutableList<Phone>{
        return list
    }
}

class Phone(private val cpu: String, private val ram: Int, private val screen: Double, private val touchID: Boolean){
    fun retCpu(): String{
        return cpu
    }
    fun retRam(): Int{
        return ram
    }
    fun retScreen(): Double{
        return screen
    }
    fun retTouchID(): Boolean{
        return touchID
    }
}