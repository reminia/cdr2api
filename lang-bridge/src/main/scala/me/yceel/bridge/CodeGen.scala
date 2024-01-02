package me.yceel.bridge

import com.squareup.javapoet.{FieldSpec, TypeName, TypeSpec}
import json2struct.GoStructAST.{Field, Struct}
import json2struct.GoType
import json2struct.GoType.GoStruct

import javax.lang.model.element.Modifier
import scala.collection.mutable

object CodeGen {

  def apply(struct: Struct): TypeSpec = {
    val classBuilder = TypeSpec.classBuilder(struct.name)
      .addModifiers(Modifier.PUBLIC)
    val ctx: mutable.Map[GoType, TypeName] = mutable.HashMap()
    struct.fields.foreach {
      case Struct(name, fields, _) =>
        //todo: class type with fields
        classBuilder.addField(
          FieldSpec.builder(JTypes.from(GoStruct(name), ctx), name)
            .addModifiers(Modifier.PRIVATE)
            .build())
      case Field.Simple(name, tpe, _) =>
        classBuilder.addField(
          FieldSpec.builder(JTypes.from(tpe, ctx), name)
            .addModifiers(Modifier.PRIVATE)
            .build())
    }
    classBuilder.build()
  }

}
