(ns riak-client.gui
  (:require [clj-riak.client :as client])
  (:import    (org.eclipse.swt SWT)
              (org.eclipse.swt.widgets Display Shell Label Composite Button)
              (org.eclipse.swt.custom ScrolledComposite)
              (org.eclipse.swt.layout FillLayout GridLayout))
  )

(defn setup-my-gui [display shell]
  (.setLayout shell (FillLayout.))

  (let [
        c1 (ScrolledComposite. shell (bit-or (bit-or SWT/H_SCROLL SWT/V_SCROLL) SWT/BORDER))
        subcontainer (Composite. c1 SWT/NONE)
        l1 (Label. subcontainer SWT/CENTER)
        l2 (Label. subcontainer SWT/CENTER)
        grid (GridLayout.)
        ]
    (set! (. grid numColumns) 1)
    (.setLayout subcontainer grid)
    (dorun
     (map
      (fn [i]
        (.setText
         (Label. subcontainer SWT/CENTER)
         (str "Fixed size label " i)))
      (range 15)))
    (.setSize subcontainer 400 400)
    
    (.setContent c1 subcontainer)
    )
  (.setSize shell 600 300)
  )

(defn setup-gui [display shell]
  "Sets up the swt gui"
  (let [
        scroll (ScrolledComposite. shell (bit-or (bit-or SWT/H_SCROLL SWT/V_SCROLL) SWT/BORDER))
        parent (Composite. scroll SWT/NONE)
        grid (GridLayout.)
        ]
    (.setExpandHorizontal scroll true)
    (.setExpandVertical scroll true)
    (set! (. grid numColumns) 1)
    (.setLayout parent grid)
    (dorun
     (map
      (fn [i]
        (let [l (Label. parent SWT/NONE)]
          (.setText l (str "Yosarian lives " i))))
      (range 20)))
    (.setSize parent (.computeSize parent SWT/DEFAULT SWT/DEFAULT))
    (.setSize scroll 200 200)
    (.setLayout shell (FillLayout.))
    (.setSize shell 200 200)
    )
  )

(defn keep-alive [display shell]
    (if (not (.isDisposed shell))
        (do
            (if (not (.readAndDispatch display))
                (.sleep display))
            (recur display shell))
        (.dispose display)))

(defn run-gui [setup-gui]
  "Function that creates the display and shell, then executes
  (setup-gui display shell), and then runs the main loop."
    (let [
        display (Display.)
        shell (Shell. display)]
    (do
        (setup-gui display shell)
        (.open shell)
        (keep-alive display shell))))

(defn start-client [rc]
  "Starts the riak client"
  (run-gui setup-my-gui)
  )
