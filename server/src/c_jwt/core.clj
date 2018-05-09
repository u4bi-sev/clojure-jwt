(ns c-jwt.core
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [compojure.handler :as h]
            [compojure.route :as route]
            [ring.middleware.json :as m-json]
            [ring.middleware.cors :refer [wrap-cors]]
            [clj-jwt.core  :refer :all]
            [clj-time.core :refer [now plus days]]))

(def hs-prv-key "secretKEYu4bi-test-1234@#")

(defn sign-key [name]
  (-> {:name "u4bi"
       :level 17
       :stats {:healing 1700
               :hungry 530
               :thirsty 950
               :stuffed 900}
       :bio nil
       :exp (plus (now) (days 1))
       :iat (now)} jwt (sign :HS256 hs-prv-key) to-str))

(defn verify-token [token]
  (-> token str->jwt (verify hs-prv-key)))

(defn decode-token [token]
  (-> token str->jwt :claims))

(defroutes handler
           (POST "/sign" [userid, accesskey]
                 (response {
                            :token (sign-key name)}))
           (POST "/verify/:token" [token]
                 (response (if-let [is-verified (verify-token token)]
                             {:verify is-verified :data (decode-token token)}
                             {:verify false})))
           (route/not-found (response
                             {:message "not found"})))
  
(def app
  (-> (h/api handler)
      (m-json/wrap-json-params)
      (m-json/wrap-json-response)
      (wrap-cors :access-control-allow-origin [#"http://127.0.0.1:5500"] ; #".*"
                 :access-control-allow-methods [:get :put :post :delete])))