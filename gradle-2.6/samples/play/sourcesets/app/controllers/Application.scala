package controllers

import play.api._
import play.api.mvc._

import org.apache.commons.lang.StringUtils

object Application extends main.java.com.mule.Controller {

  def index = Action {
    Ok("   Your new application is ready.   ")
  }

}
