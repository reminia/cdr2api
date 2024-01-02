package me.yceel.bridge

import com.squareup.javapoet.{ArrayTypeName, ClassName, TypeName}
import json2struct.GoType

import scala.collection.mutable

// java types
object JTypes {

  def from(tpe: GoType, ctx: mutable.Map[GoType, TypeName] = mutable.Map.empty): TypeName =
    tpe match {
      case GoType.GoByte    => TypeName.BYTE
      case GoType.GoInt     => TypeName.INT
      case GoType.GoInt32   => TypeName.INT
      case GoType.GoUInt64  => TypeName.LONG
      case GoType.GoFloat32 => TypeName.FLOAT
      case GoType.GoChar    => TypeName.CHAR
      case GoType.GoString  => ClassName.bestGuess("String")
      case GoType.GoBool    => TypeName.BOOLEAN
      case GoType.GoArray(element) =>
        val eleType = ctx.getOrElseUpdate(element, from(element, ctx))
        ArrayTypeName.of(eleType)
      case GoType.GoStruct(name) => ctx.getOrElseUpdate(tpe, ClassName.bestGuess(name))
      case GoType.GoAny          => TypeName.OBJECT
    }

}
