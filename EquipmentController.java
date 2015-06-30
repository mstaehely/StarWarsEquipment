import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Controller object for equipment.
 * 
 * @author Matthew Staehely
 * @version 1
 */

public class EquipmentController extends JPanel{
	private static final long serialVersionUID = 1L;
    private ItemList item_list;
    private ItemView itemPane;
    private EquipmentView statPane;
    private ModView modPane;
    private ModTextView modText;
    private CombinedItemView combinedText;
    private JPanel panel, panel2, panel3;
    private Border loweredBevel;
    private JButton combine;
    
    /**
     * Constructs an EquipmentController object.
     * 
     * @param item_list the ItemList this Controller controls.
     */
    public EquipmentController(ItemList item_list){
        // Initializes all the panes, sets up graphics.
        this.item_list = item_list;
        itemPane = new ItemView(this.item_list);
        statPane = new EquipmentView(this.item_list);
        modPane = new ModView(this.item_list);
        modText = new ModTextView(this.item_list);
        combinedText = new CombinedItemView(this.item_list);
        loweredBevel = BorderFactory.createLoweredBevelBorder();
        combine = new JButton("Combine");
        combine.addActionListener(new ButtonListener(this.item_list));
        this.setLayout(new BorderLayout());
        
        // Sets up top panel.
        panel = new JPanel();
        panel.setBorder(loweredBevel);
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 250));
        panel.add(itemPane, BorderLayout.NORTH);
        panel.add(statPane, BorderLayout.CENTER);
        
        // Sets up middle panel.
        panel2 = new JPanel();
        panel2.setBorder(loweredBevel);
        panel2.setLayout(new BorderLayout());
        panel2.setPreferredSize(new Dimension(400, 250));
        panel2.add(modPane, BorderLayout.NORTH);
        panel2.add(modText, BorderLayout.CENTER);
        panel2.add(combine, BorderLayout.SOUTH);

        
        // Sets up lower panel.
        panel3 = new JPanel();
        panel3.setBorder(loweredBevel);
        panel3.setLayout(new BorderLayout());
        panel3.setPreferredSize(new Dimension(400, 250));
        panel3.add(combinedText, BorderLayout.CENTER);
        
        // Adds both panels to the frame.
        this.add(panel, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
    }
}